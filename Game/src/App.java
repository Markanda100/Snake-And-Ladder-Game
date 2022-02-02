public class App {
    public static void main(String[] args) {

            Game game = new Game("Navin", "Meher");
            while (!game.isOver) {
                game.proceed();

        }
    }
}
