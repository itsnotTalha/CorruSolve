public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(3);

        // Adding Transactions
        blockchain.addTransaction("Alice", "Bob", 50);
        blockchain.mineBlock();

        blockchain.addTransaction("Charlie", "Dave", 30);
        blockchain.mineBlock();
        blockchain.addTransaction("Charlie", "Talha", 60);
        blockchain.mineBlock();
        blockchain.addTransaction("Charlie", "Jubayer", 30);
        blockchain.mineBlock();

        // 🔍 Find a Block by Index
        int searchIndex = 4;
        Block blockByIndex = blockchain.getBlockByIndex(searchIndex);
        if (blockByIndex != null) {
            System.out.println("\n🔎 Block Found by Index " + searchIndex + ":");
            System.out.println("Hash: " + blockByIndex.getHash());
            System.out.println("Previous Hash: " + blockByIndex.getPreviousHash());
            System.out.println("Transactions: " + blockByIndex.getTransactions());
        }

        // 🔍 Find a Block by Hash
        String searchHash = blockByIndex.getHash();
        Block blockByHash = blockchain.getBlockByHash(searchHash);
        if (blockByHash != null) {
            System.out.println("\n🔎 Block Found by Hash " + searchHash + ":");
            System.out.println("Index: " + searchIndex);
            System.out.println("Transactions: " + blockByHash.getTransactions());
        }
    }
}
