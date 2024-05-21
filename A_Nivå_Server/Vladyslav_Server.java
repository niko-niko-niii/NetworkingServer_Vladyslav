import java.net.*;



public class Vladyslav_Server {
    public static void main(String args[]) throws Exception {
// Default port number we are going to use
        int portnumber = 8000;
        if (args.length >= 1) {

            portnumber = Integer.parseInt(args[0]);
        }

// Create a MulticastSocket
        MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);

        System.out.println("Multicast Socket is created at port" + portnumber);
// Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");
// getByName- returns IP address of given host
        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");
        boolean infinite = true;
// Continually receives data and prints them
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
             double answer;
            try{
              answer =findFunc(msg);
                System.out.println("lösning för "+ msg+" är "+ answer);
            }catch (Exception e){
                System.out.println("no mathematical function found");

            }

        }
        serverMulticastSocket.close();
    }

    public static double findFunc(String input) {
        char[] funcs = {'+', '-', '*', '/'};

        // Loopa genom varje tecken i input-strängen
        for (int i = 0; i < input.length(); i++) {
            // Kolla om nuvarande tecken är ett av de matematiska tecknen
            for (char func : funcs) {
                if (input.charAt(i) == func) {
                    double part1 = Double.parseDouble(input.substring(0, i));
                    double part2 = Double.parseDouble(input.substring(i ));

                    switch (input.charAt(i)) {
                        case '+':return part1 + part2;
                        case '-':return part1 - part2;
                        case '*':return part1 * part2;
                        case '/':return part1 / part2;
                    }
                }
            }
        }
        // Om inget matematiskt tecken hittas, returnera ett tomt tecken
        return '\0';
    }
}


