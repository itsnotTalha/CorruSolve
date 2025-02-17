import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Block {
    private int index;
    private long timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private String name;
    private int nonce;
    private List<Integer> referencedBlocks; // Store block indexes

    public Block(int index, List<Transaction> transactions, String previousHash, String name) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.name = name;
        this.referencedBlocks = new ArrayList<>(); // Initialize as empty list
        this.hash = calculateHash();
    }

    public void addReferencedBlock(int blockIndex) {
        referencedBlocks.add(blockIndex);
        this.hash = calculateHash(); // Recalculate hash to reflect changes
    }

    public String calculateHash() {
        StringBuilder transactionData = new StringBuilder();
        for (Transaction t : transactions) {
            transactionData.append(t.toString());
        }

        StringBuilder referencedData = new StringBuilder();
        for (int refIndex : referencedBlocks) {
            referencedData.append(refIndex);
        }

        String input = String.valueOf(index) +  // Convert int to String properly
                timestamp +              // timestamp is already a long, auto-converted
                transactionData +         // StringBuilder is converted automatically
                previousHash +            // String
                name +                    // String
                nonce +                    // int auto-converted
                referencedData;            // StringBuilder auto-converted

        return applySha256(input);

    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! Hash: " + hash);
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Integer> getReferencedBlocks() { return referencedBlocks; } // Getter for referenced blocks
}
