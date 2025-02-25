package controller;

import domain.Car;
import domain.Game;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;


public class GameController {

    final Game game = new Game();

    public void start() {
        int turns = game.getTurns();
        List<Car> cars = game.getCars();
        for (int i = 0; i < turns; i++) {
            for (Car car : cars) {
                car.goOrStop();
                OutputView.printNow(cars);
            }
        }
        OutputView.printEnd(decideWinner(cars));
    }

    private List<String> decideWinner(List<Car> carList) {
        Car maxCar = carList.stream()
                .max(Car::compareTo)
                .orElseThrow(() -> new IllegalArgumentException());

        List<String> winnerList = carList.stream()
                .filter(maxCar::isSamePosition)
                .map(Car::getName)
                .collect(Collectors.toList());

        return winnerList;

    }

}
