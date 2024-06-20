package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.mongo;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.utils.Constants;

public class GameDTO {
	
    private int dieOne;
    private int dieTwo;
    private int sumDice;
    private boolean result;
    

    public GameDTO() {
		super();
	}

	public GameDTO(int dieOne, int dieTwo) {
        this.dieOne = dieOne;
        this.dieTwo = dieTwo;
        this.sumDice = dieOne + dieTwo;
        this.result = (this.sumDice == Constants.Results.WIN_RESULT);
    }

	public int getDieOne() {
		return dieOne;
	}

	public void setDieOne(int dieOne) {
		this.dieOne = dieOne;
	}

	public int getDieTwo() {
		return dieTwo;
	}

	public void setDieTwo(int dieTwo) {
		this.dieTwo = dieTwo;
	}

	public int getSumDice() {
		return sumDice;
	}

	public void setSumDice(int sumDice) {
		this.sumDice = sumDice;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "GameDTO [dieOne=" + dieOne + ", dieTwo=" + dieTwo + ", sumDice=" + sumDice + ", result=" + result
				+ "]";
	}

}

