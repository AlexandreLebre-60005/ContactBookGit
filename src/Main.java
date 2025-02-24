import contactBook.Contact;
import contactBook.ContactBook;

import java.util.Scanner;


public class Main {

    //Constantes que definem os comandos
    public static final String ADD_CONTACT    = "AC";
    public static final String REMOVE_CONTACT = "RC";
    public static final String GET_PHONE      = "GP";
    public static final String GET_EMAIL      = "GE";
    public static final String SET_PHONE      = "SP";
    public static final String SET_EMAIL      = "SE";
    public static final String LIST_CONTACTS  = "LC";
    public static final String CONTACT_NUMBER = "GN";
    public static final String QUIT           = "Q";
    public static final String REPEATED_PHONES      = "EP";

    //Constantes que definem as mensagens para o utilizador
    public static final String CONTACT_EXISTS = "contactBook.Contact already exists.";
    public static final String NAME_NOT_EXIST = "contactBook.Contact does not exist.";
    public static final String CONTACT_ADDED = "contactBook.Contact added.";
    public static final String CONTACT_REMOVED = "contactBook.Contact removed.";
    public static final String CONTACT_UPDATED = "contactBook.Contact updated.";
    public static final String BOOK_EMPTY = "contactBook.Contact book empty.";
    public static final String QUIT_MSG = "Goodbye!";
    public static final String COMMAND_ERROR = "Unknown command.";
    private static final String CONTACT_NOT_EXIST = "Phone number does not exist.";
    private static final String ALL_DIFF_NUMBERS = "All contacts have different phone numbers.";
    private static final String EXISTS_REPEATED = "There are contacts that share phone numbers.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ContactBook cBook = new ContactBook();
        String comm = getCommand(in);

        while (!comm.equals(QUIT)){
            switch (comm) {
                case ADD_CONTACT:
                    addContact(in,cBook);
                    break;
                case REMOVE_CONTACT:
                    deleteContact(in,cBook);
                    break;
                case GET_PHONE:
                    getPhone(in,cBook);
                    break;
                case GET_EMAIL:
                    getEmail(in,cBook);
                    break;
                case SET_PHONE:
                    setPhone(in,cBook);
                    break;
                case SET_EMAIL:
                    setEmail(in,cBook);
                    break;
                case CONTACT_NUMBER:
                    contactGivenNumber(in, cBook);
                    break;
                case LIST_CONTACTS:
                    listAllContacts(cBook);
                    break;
                case REPEATED_PHONES:
                    repeatedPhone(in,cBook);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
            }
            System.out.println();
            comm = getCommand(in);
        }
        System.out.println(QUIT_MSG);
        System.out.println();
        in.close();
    }

    private static void repeatedPhone(Scanner in, ContactBook cBook) {
        if(cBook.getNumberOfContacts()==0){
            System.out.println(ALL_DIFF_NUMBERS);
        } else {
            int count = 0;
            while(count< cBook.getNumberOfContacts()){
                int c = -1;
                cBook.initializeIterator();
                for(int i = 0; i<=count;i++){
                     c = cBook.next().getPhone();
                }
                while(cBook.hasNext()){
                    int c2 = cBook.next().getPhone();
                    if(c == c2){
                        System.out.println(EXISTS_REPEATED);
                        return;
                    }
                }
                count++;
        }
            System.out.println(ALL_DIFF_NUMBERS);

        }

    }

    private static String getCommand(Scanner in) {
        String input;

        input = in.nextLine().toUpperCase();
        return input;
    }

    private static void addContact(Scanner in, ContactBook cBook) {
        String name, email;
        int phone;

        name = in.nextLine();
        phone = in.nextInt(); in.nextLine();
        email = in.nextLine();
        if (!cBook.hasContact(name)) {
            cBook.addContact(name, phone, email);
            System.out.println(CONTACT_ADDED);
        }
        else System.out.println(CONTACT_EXISTS);
    }

    private static void deleteContact(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.deleteContact(name);
            System.out.println(CONTACT_REMOVED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    private static void getPhone(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getPhone(name));
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    private static void getEmail(Scanner in, ContactBook cBook) {
        String name;
        name = in.nextLine();
        if (cBook.hasContact(name)) {
            System.out.println(cBook.getEmail(name));
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    private static void setPhone(Scanner in, ContactBook cBook) {
        String name;
        int phone;
        name = in.nextLine();
        phone = in.nextInt(); in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setPhone(name,phone);
            System.out.println(CONTACT_UPDATED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    private static void setEmail(Scanner in, ContactBook cBook) {
        String name;
        String email;
        name = in.nextLine();
        email = in.nextLine();
        if (cBook.hasContact(name)) {
            cBook.setEmail(name,email);
            System.out.println(CONTACT_UPDATED);
        }
        else System.out.println(NAME_NOT_EXIST);
    }

    private static void listAllContacts(ContactBook cBook) {
        if (cBook.getNumberOfContacts() != 0) {
            cBook.initializeIterator();
            while( cBook.hasNext() ) {
                Contact c = cBook.next();
                System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
            }
        }
        else System.out.println(BOOK_EMPTY);
    }

    private static void contactGivenNumber(Scanner in, ContactBook cBook) {
        int phone;
        phone = in.nextInt();
        in.nextLine();
        String contact;
        //System.out.println("entsGDi");
        contact = cBook.getContact(phone);
        //System.out.println("entrei");
        if(contact != null){
            System.out.println(contact);
        }
        else System.out.println(CONTACT_NOT_EXIST);
    }
}
