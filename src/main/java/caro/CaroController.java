package caro;

public class CaroController {
    private final CaroModel caroModel;
    private static CaroController controller = null;
    private CaroController() {
        caroModel = CaroModel.getInstance();
    }
    public static CaroController getInstance() {
        if(controller == null) {
            controller = new CaroController();
        }
        return controller;
    }
    public int getSizeMatrix() {
        return caroModel.getSizeMatrix();
    }
    public void playerMove(int x, int y) {
        // Event Player Click
        CaroModel.getInstance().playerMove(x, y);
        CaroView.getInstance().playerMove(x, y);
        int [][] checkStatusGame = CaroModel.getInstance().checkStatusGame(x, y);
        if(checkStatusGame != null) {
            CaroView.getInstance().endGame(checkStatusGame);
        }
        else {
            this.botMove();
        }
    }
    public void botMove() {
        // Event Bot execute
        int bestMove[] = CaroModel.getInstance().findBestMove();
        CaroModel.getInstance().botMove(bestMove[0], bestMove[1]);
        CaroView.getInstance().botMove(bestMove[0], bestMove[1]);
        int [][] checkStatusGame = CaroModel.getInstance().checkStatusGame(bestMove[0], bestMove[1]);
        if(checkStatusGame != null) {
            CaroView.getInstance().endGame(checkStatusGame);
        }
    }
}
