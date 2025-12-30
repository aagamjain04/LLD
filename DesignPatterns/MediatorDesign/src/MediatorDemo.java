public class MediatorDemo {
    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();

        User alice = new ConcreteUser(chatRoom,"Alice");
        User bob = new ConcreteUser(chatRoom, "Bob");
        User charlie = new ConcreteUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.send("Hello everyone!");
        System.out.println();
        bob.send("Hi Alice!");
    }
}
