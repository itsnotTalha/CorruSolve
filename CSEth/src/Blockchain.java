import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int difficulty;
    private TransactionPool transactionPool;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        this.transactionPool = new TransactionPool();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, new ArrayList<>(), "0", "Genesis Block");
    }

    public void addTransaction(String sender, String receiver, double amount) {
        Transaction transaction = new Transaction(sender, receiver, amount);
        transactionPool.addTransaction(transaction);
        System.out.println("✅ Transaction added: " + transaction);
    }

    public void mineBlock() {
        if (transactionPool.getTransactions().isEmpty()) {
            System.out.println("⚠️ No transactions to mine.");
            return;
        }

        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(chain.size(), new ArrayList<>(transactionPool.getTransactions()), previousBlock.getHash(), "Block " + chain.size());
        newBlock.mineBlock(difficulty);

        chain.add(newBlock);
        transactionPool.clear();
        System.out.println("✅ Block " + newBlock.getHash() + " added to the blockchain!");
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println("\n🔗 Block Hash: " + block.getHash());
            System.out.println("🔙 Previous Hash: " + block.getPreviousHash());
            System.out.println("📜 Transactions:");
            for (Transaction t : block.getTransactions()) {
                System.out.println("  - " + t);
            }
        }
    }

    public Block getBlockByIndex(int index) {
        if (index < 0 || index >= chain.size()) {
            System.out.println("❌ Block not found!");
            return null;
        }
        return chain.get(index);
    }

    public Block getBlockByHash(String hash) {
        for (Block block : chain) {
            if (block.getHash().equals(hash)) {
                return block;
            }
        }
        System.out.println("❌ Block not found!");
        return null;
    }
}
