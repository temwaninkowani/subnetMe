package subnet.project.classes;

public class helperMethods {

   public static String performClassASubnetting(String input, int subnetBits){
    
        int totalBits = 32;
        int hostBits = totalBits - 8 - subnetBits;
        int numberOfSubnets = (int)Math.pow(2,subnetBits);
        int numberOfHosts = (int)Math.pow(2, hostBits) - 2;
        String subnetMask = calculateSubnetMask(8 + subnetBits);

        String ClassA ="Class A Subnetting:\n" +
        "Subnet Mask: " + subnetMask + "\n" +
        "Number of Subnets: " + numberOfSubnets + "\n" +
        "Hosts per Subnet: " + numberOfHosts;

        return ClassA;

        
   }


   public static String performClassBSubnetting(String input, int subnetBits) {
    int totalBits = 32;
    int hostBits = totalBits - 16 - subnetBits; // Class B has 16 bits reserved for the network
    int numberOfSubnets = (int) Math.pow(2, subnetBits);
    int numberOfHosts = (int) Math.pow(2, hostBits) - 2; // Subtract 2 for network and broadcast addresses
    String subnetMask = calculateSubnetMask(16 + subnetBits);

    String ClassB = "Class B Subnetting:\n" +
           "Subnet Mask: " + subnetMask + "\n" +
           "Number of Subnets: " + numberOfSubnets + "\n" +
           "Hosts per Subnet: " + numberOfHosts;

    return ClassB;
}


public static String performClassCSubnetting(String input, int subnetBits) {
    int totalBits = 32;
    int hostBits = totalBits - 24 - subnetBits; // Class C has 24 bits reserved for the network
    int numberOfSubnets = (int) Math.pow(2, subnetBits);
    int numberOfHosts = (int) Math.pow(2, hostBits) - 2; // Subtract 2 for network and broadcast addresses
    String subnetMask = calculateSubnetMask(24 + subnetBits);

    String ClassC = "Class C Subnetting:\n" +
           "Subnet Mask: " + subnetMask + "\n" +
           "Number of Subnets: " + numberOfSubnets + "\n" +
           "Hosts per Subnet: " + numberOfHosts;

    return ClassC;
}


private static String calculateSubnetMask(int networkBits) {
    int mask = 0xffffffff << (32 - networkBits);
    return String.format("%d.%d.%d.%d", 
            (mask >> 24) & 0xff, 
            (mask >> 16) & 0xff, 
            (mask >> 8) & 0xff, 
            mask & 0xff);
}






    
}
