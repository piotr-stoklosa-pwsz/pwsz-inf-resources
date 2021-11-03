import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class WinnerWasCalled extends Exception {
}

class WrongGameConfig extends Exception {
    WrongGameConfig(String message) {
        super(message);
    }
}

class Log {

	public static void info() {
		System.out.println("");
	}

	public static void info(String message) {
		System.out.println(message);
	}

}

class Dice {

    private int sides;

    public Dice(int sides) throws WrongGameConfig {
        if(sides < 1)
            throw new WrongGameConfig("Incorrect sides number.");
        this.sides = sides;
    }

	public int roll() {
		Random rand = new Random();
		int result = rand.nextInt(sides) + 1;
		Log.info("Dice roll: " + result);
		return result;
	}

}

class Pawn {

	private int position;
	private String name;

	public Pawn(String name) {
		this.position = 0;
		this.name = name;
		
		Log.info(this.name + " joined the game.");
	}

    public int move(int steps) {
        this.position += steps;
        return this.position;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

}

class Board {

	private int max_position;
	private int maxTurns;
	private ArrayList<Pawn> pawns;
	private Dice dice;
	private Pawn winner;
	private int turnsCounter;
    private int fields;

	public Board() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int playerNum = 0;
        while(this.pawns == null) {
            Log.info("Please enter the number of players (between 3 and 10):");
		    try {
                playerNum = Integer.parseInt(reader.readLine());
                if(playerNum < 3 || playerNum > 10)
                    throw new WrongGameConfig("Incorrect players number.");
		        this.pawns = new ArrayList<Pawn>();
            } catch(WrongGameConfig exception) {
                Log.info("Invalid configuration: " + exception.toString());
            } catch(NumberFormatException exception) {
                Log.info("Invalid input: " + exception.toString());
            }
        }
        String playerName;
        for(int i = 1; i <= playerNum; i++) {
            Log.info("Please enter the name of Player " + i + ":");
            playerName = reader.readLine();
            this.pawns.add(new Pawn(playerName));
        }
        int sides;
		while(this.dice == null) {
            Log.info("Please enter the number of sides of a dice:");
            try {
                sides = Integer.parseInt(reader.readLine());
                this.dice = new Dice(sides);
            } catch(WrongGameConfig exception) {
                Log.info("Invalid configuration: " + exception.toString());
            } catch(NumberFormatException exception) {
                Log.info("Invalid input: " + exception.toString());
            }
        }

        this.max_position = 0;
        while(this.max_position == 0) {
            Log.info("Please enter the number of fields (>0):");
            try {
                this.max_position = Integer.parseInt(reader.readLine());
                if(this.max_position <= 0) {
                    this.max_position = 0;
                    throw new WrongGameConfig("Wrong number of fields.");
                }
            } catch(WrongGameConfig exception) {
                Log.info("Invalid configuration: " + exception.toString());
            } catch(NumberFormatException exception) {
                Log.info("Invalid input: " + exception.toString());
            }
        }
        this.maxTurns = -1;
        while(this.maxTurns == -1) {
            Log.info("Please enter the number of max turns (0 for indefinite):");
            try {
                this.maxTurns = Integer.parseInt(reader.readLine());
                if(this.maxTurns < 0) {
                    this.maxTurns = -1;
                    throw new WrongGameConfig("Wrong number of max turns.");
                }
            } catch(WrongGameConfig exception) {
                Log.info("Invalid configuration: " + exception.toString());
            } catch(NumberFormatException exception) {
                Log.info("Invalid input: " + exception.toString());
            }
        }

		this.winner = null;
		this.turnsCounter = 0;
	}

	public void performTurn() throws WinnerWasCalled {
		this.turnsCounter++;
		Log.info();
		Log.info("Turn " + this.turnsCounter);

        int topPosition = 0;
        Pawn leader = null;
		for(Pawn pawn : this.pawns) {
			int rollResult = this.dice.roll();
			int newPosition = pawn.move(rollResult);
            if(newPosition > topPosition) {
                topPosition = newPosition;
                leader = pawn;
            }
			Log.info(pawn.getName() + " new position: " + newPosition);
			if(newPosition >= this.max_position) {
				this.winner = pawn;
				throw new WinnerWasCalled();
            }
		}
	    if(this.maxTurns > 0 && this.maxTurns == this.turnsCounter) {
            this.winner = leader;
            throw new WinnerWasCalled();
        }
	}

    public String getWinnersName() {
        if(winner == null)
            return "No one";
        return winner.getName();
    }

}

public class Main {

	public static void main(String[] args) {
        Board board;
		try {
            board = new Board();
		} catch(IOException exception) {
            Log.info("Text input issue; Aborting.");
            return;
        }

		try {
			while(true) {
				board.performTurn();
			}
		} catch(WinnerWasCalled exception) {
			Log.info();
			Log.info(board.getWinnersName() + " won.");
        }
	}

}
