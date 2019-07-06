/**
 * Created by ghqing on 17/1/4.
 */
public class GameHistory {

    private Animal[][][] animalHistory = new Animal[100][][];
    private int currentStep = -1;

    public GameHistory(){

    }

    public void addRecord(Animal[][] animal){

        currentStep++;
        animalHistory[currentStep] = new Animal[animal.length][animal[0].length];
        animalHistory[currentStep] = copyAnimal(animalHistory[currentStep], animal);
    };

    public Animal[][] undo(Animal[][] animal){

        if (currentStep > 0) {
            currentStep--;
            animal = copyAnimalHistory(animalHistory[currentStep], animal);
        }

        return animal;
    }

    public Animal[][] restart(Animal[][] animal){

        currentStep = 0;
        animal = copyAnimalHistory(animalHistory[0], animal);

        return animal;
    }

    public Animal[][] copyAnimal(Animal[][] animalHistory, Animal[][] animal){

        for (int i = 0; i < animal.length; i++){
            for (int j = 0; j < animal[0].length; j++){
                animalHistory[i][j] = new Animal();
                animalHistory[i][j] = animal[i][j];
            }
        }

        return animalHistory;
    }

    public Animal[][] copyAnimalHistory(Animal[][] animalHistory, Animal[][] animal){

        for (int i = 0; i < animalHistory.length; i++){
            for (int j = 0; j < animalHistory[0].length; j++){
                animal[i][j] = animalHistory[i][j];
            }
        }

        return animal;
    }
}
