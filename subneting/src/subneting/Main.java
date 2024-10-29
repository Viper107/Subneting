package subneting;
import java.util.Scanner;

public class Main {
	
	//Method to calculate subnet details
	public static void calculate_subnets(int oct1, int oct2, int oct3, int oct4, int subnet_needed) {
		int defaultMaskBit =24;
		int newMaskBits = defaultMaskBit + (int) Math.ceil(Math.log(subnet_needed)/ Math.log(2));
		int subnetMask = (0xFFFFFFFF << (32 - newMaskBits) & 0xFFFFFFFF);
		
		//Calculate subnet mask in dotted decimal format
	    int mask_oct1 = (subnetMask >> 24) & 0xFF;
	    int mask_oct2 = (subnetMask >> 16) & 0xFF;
	    int mask_oct3 = (subnetMask >> 8) & 0xFF;
	    int mask_oct4 =  subnetMask & 0xFF;
	    
	    System.out.printf("New Subnet Mask: %d.%d.%d.%d\n", mask_oct1, mask_oct2,mask_oct3,mask_oct4);
		
	    int hosts_per_subnet = (int) Math.pow(2, 32 - newMaskBits)-2;
	    int increment =256 / (int) Math.pow(2,  newMaskBits- defaultMaskBit);
	    
	    System.out.printf("Number of Hosts per Subnet: %d\n\n",hosts_per_subnet);
	    System.out.print("Subnets:\n");
	    
	    //Generate and print network address for each subnet
	    for(int i =0; i < subnet_needed;i++) {
	    	int subnet_oct4 = i*increment;
	    	System.out.printf("Subnet %d: %d.%d.%d.%d\n", i+1,oct1,oct2,oct3,subnet_oct4);
	    }
	    
	}
	
	public static void main(String[] args) {
		int oct1,oct2,oct3,oct4,subnet_needed;
		Scanner scanner = new Scanner(System.in);
		
		//Take IP address as input
		System.out.print("Enter IP Address(eg: 192.168.125.15): ");
		String ipInput = scanner.nextLine();
		
		//Parse the input
		String[] octets = ipInput.split("\\.");
		oct1 = Integer.parseInt(octets[0]);
		oct2 = Integer.parseInt(octets[1]);
		oct3 = Integer.parseInt(octets[2]);
		oct4 = Integer.parseInt(octets[3]);
		
	    //Take the number of subnets needed
		System.out.println("Enter the number of sybnets required: ");
		subnet_needed = scanner.nextInt();
		
		
		//Calclulate and display subnet details
		calculate_subnets(oct1,oct2,oct3,oct4,subnet_needed);
		
		scanner.close();

	}

}
