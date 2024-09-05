import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Switch mySwitch = new Switch();
        mySwitch.run();
//        Scanner scanner = new Scanner(System.in);
//        String scase = scanner.nextLine();
//        while (!scase.equals("end")) {
//            System.out.println(scase.matches("(y|Y)(e|E)(l|L)(l|L)(o|O)(w|W)"));
//            scase = scanner.nextLine();
//        }
    }
}
class Payload {

    private byte length;
    private String context;

    public Payload(byte length, char[] payloadBytes) {
        this.length = length;
        this.context = new String(payloadBytes);
    }

    public String getContext() {
        return context;
    }

    public byte getLength() {
        return length;
    }

    public void encode(byte key) {
        //TODO
        if (key == 00000000)
            return;
        int asciiCode;
        int newAsciiCode;
        String newContext = new String("");
        for (int i = 0; i < context.length(); i++) {
            asciiCode = context.charAt(i);
            newAsciiCode = asciiCode + key;
            newContext += (char) newAsciiCode;
        }
        context = newContext;
    }
}

    class Header {

        private byte srcAddress;
        private byte dstAddress;
        private byte payloadLength;
        private byte key;

        public Header(byte srcAddress, byte dstAddress, byte payloadLength, byte key) {
            this.srcAddress = srcAddress;
            this.dstAddress = dstAddress;
            this.payloadLength = payloadLength;
            this.key = key;
        }


        public byte getPayloadLength() {
            return payloadLength;
        }

        public byte getSrcAddress() {
            return srcAddress;
        }

        public byte getKey() {
            return key;
        }

        public byte getDstAddress() {
            return dstAddress;
        }

    }

    class Packet {

        private Header header;
        private Payload payload;

        public Packet(Header header, Payload payload) {
            this.header = header;
            this.payload = payload;
        }

        public short forward() {
            //TODO
            String lowerCaseContext = payload.getContext().toLowerCase();
            if (lowerCaseContext.contains("yellow") || lowerCaseContext.contains("karma") || lowerCaseContext.contains("hooman")
             || lowerCaseContext.contains("tehran") || lowerCaseContext.contains("gun") || lowerCaseContext.contains("sadness")
             || lowerCaseContext.contains("pride") || lowerCaseContext.contains("language") || lowerCaseContext.contains("laptop")
             || lowerCaseContext.contains("stalker")){
                return 0;
            }
            if (header.getDstAddress() == 110 || header.getDstAddress() == 120)
                return 1;
            else if (header.getDstAddress() == 50 || header.getDstAddress() == 60)
                return 2;
            else if (header.getDstAddress() == 90 || header.getDstAddress() == 100)
                return 3;
            else if (header.getDstAddress() == 80 || header.getDstAddress() == 70)
                return 4;
            else
                return 0;
        }

        public void encode() {
            payload.encode(header.getKey());
        }

        public Header getHeader() {
            return header;
        }

        public Payload getPayload() {
            return payload;
        }
    }

    class Switch {


        private Parser parser;
        private Deparser deparser;
        private Scanner inputScanner;
        private Packet packet;
        private byte[] outputBuffer;
        private short outputPort;


        public Switch() {
            this.inputScanner = new Scanner(System.in);
            this.parser = new Parser(inputScanner);
            this.deparser = new Deparser();
        }

        public void run() {
            while (inputScanner.hasNext()) {
                packet = parser.parse();
                packet.encode();
                outputPort = packet.forward();
                outputBuffer = deparser.deparse(packet);
                showOutputPacket(outputBuffer, outputPort);
            }
        }


        private String toBitString(final byte[] b) {
            final char[] bits = new char[8 * b.length];
            for (int i = 0; i < b.length; i++) {
                final byte byteval = b[i];
                int bytei = i << 3;
                int mask = 0x1;
                for (int j = 7; j >= 0; j--) {
                    final int bitval = byteval & mask;
                    if (bitval == 0) {
                        bits[bytei + j] = '0';
                    } else {
                        bits[bytei + j] = '1';
                    }
                    mask <<= 1;
                }
            }
            return String.valueOf(bits);
        }

        private void showOutputPacket(byte[] outputBuffer, short outputPort) {
            System.out.println(outputPort + ": " + toBitString(outputBuffer));
        }

    }

    class Deparser {


        public byte[] deparse(Packet packet) {
            byte[] headerBytes = deparseHeader(packet.getHeader());
            byte[] payloadBytes = deparsePayload(packet.getPayload());
            byte[] output = new byte[headerBytes.length + payloadBytes.length];
            System.arraycopy(headerBytes, 0, output, 0, headerBytes.length);
            System.arraycopy(payloadBytes, 0, output, headerBytes.length, payloadBytes.length);
            return output;
        }

        public byte[] deparseHeader(Header header) {
            //TODO
            byte[] result = new byte[4];
                result[0] = header.getSrcAddress();
                result[1] = header.getDstAddress();
                result[2] = header.getPayloadLength();
                result[3] = 0;
                return result;
        }

        public byte[] deparsePayload(Payload payload) {
            //TODO
            byte result[] = new byte[payload.getLength()];
            char[] contextChars = payload.getContext().toCharArray();
            for (int i = 0; i < contextChars.length; i++) {
                result[i] = (byte) contextChars[i];
            }
            return result;
        }

//        public String baseConvertor(byte number){
//            String result = new String("");
//            for (int i = 7; i >= 0; i--) {
//                if (number >= Math.pow(2, i)){
//                    number -= Math.pow(2, i);
//                    result += '1';
//                }else {
//                    result += '0';
//                }
//            }
//            return result;
//        }

    }

    class Parser {

        static final int HEADER_LENGTH = 4;
        private Scanner inputScanner;
        private byte[] headerBuffer;


        public Parser(Scanner scanner) {
            inputScanner = scanner;
            headerBuffer = new byte[HEADER_LENGTH];
        }

        public Packet parse() {
            Header header = parseHeader(this.inputScanner);
            Payload payload = parsePayload(this.inputScanner, header.getPayloadLength());
            return new Packet(header, payload);
        }

        public Header parseHeader(Scanner inputScanner) {
            //TODO
            Header result = new Header(baseConvertor(inputScanner.nextLine())
                    , baseConvertor(inputScanner.nextLine()), baseConvertor(inputScanner.nextLine()),
                    baseConvertor(inputScanner.nextLine()));
            return result;
        }

        public Payload parsePayload(Scanner inputScanner, byte payloadLength) {
            //TODO
            String [] payloadBytes = new String[payloadLength];
            byte[] asciiPayloadBytes = new byte[payloadLength];
            char[] context = new char[payloadLength];
            int i = 0;
            while (i < payloadLength){
                payloadBytes[i] = inputScanner.nextLine();
                asciiPayloadBytes[i] = baseConvertor(payloadBytes[i]);
                context[i] = (char) asciiPayloadBytes[i];
                i++;
            }
            Payload result = new Payload(payloadLength, context);
            return result;
        }

        public byte baseConvertor(String number){
            byte result = 0;
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '1'){
                    result += Math.pow(2, number.length() - i - 1);
                }
            }
            return result;
        }
    }
