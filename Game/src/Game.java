public class Game {
    private final WorkFlow workFlow;
    private final Box[][] board = new Box[10][10];
    public boolean isOver = false;

    public Game(String name1, String name2){
        Util.makeBoard(board);
        Util.makeSnakes(board);
        Util.makeLadders(board);
        workFlow = Util.initializeWorkFlow(name1, name2);
    }

    public void proceed() {
        if(this.isOver){
            System.out.println("GAME IS OVER");
            return;
        }
        int roll = Util.diceRoll(workFlow);

        Util.log(Constants.ROLL, this.workFlow);
        makeMove(workFlow);
        Util.log(Constants.AFTER_MOVE, this.workFlow);

        if(Util.isKill(workFlow)){
            Util.log(Constants.KILL, this.workFlow);
            if(!workFlow.turnFirstPlayer){
                workFlow.player1.position = board[0][0];
            }
            else {
                workFlow.player2.position = board[0][0];
            }
        }

        if(workFlow.newPosition.x == 9 && workFlow.newPosition.y == 9){
            this.isOver  = true;
            Util.log(Constants.WIN, workFlow);
        }
        System.out.println();
        Util.changeTurn(workFlow);
    }

    private void makeMove(WorkFlow workFlow) {
        Util.log(Constants.BEFORE_MOVE, this.workFlow);
        if(Util.initialCheck(workFlow)){
            if(workFlow.roll != 6)return;
            int roll = Util.diceRoll(workFlow);
            System.out.println("You got a 6, you're good to go!!!");
            Util.log(Constants.ROLL, this.workFlow);
        }
        workFlow.newPosition = Util.calculateNewPosition(workFlow);
        if(Util.isOverShooting(workFlow))return;
        Util.log(Constants.SHOULD_GO, workFlow);
        Util.checkSnake(board, workFlow);
        Util.checkLadder(board, workFlow);
        if(workFlow.turnFirstPlayer)workFlow.player1.position = workFlow.newPosition;
        else workFlow.player2.position = workFlow.newPosition;
    }
}
