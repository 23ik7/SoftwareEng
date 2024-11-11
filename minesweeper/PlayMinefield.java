package minesweeper;

public class PlayMinefield {
    public static void main(String[] args) {
        Minesweeper f1 = new Minesweeper();
        /*String correct1 = "***\n...\n***";
        String correct2 = "*.*";
        String inCorrect1 = "+++\n---\n+++";
        String inCorrect2 = "****\n...\n***";
        String inCorrect3 = "**\n..\n**";
        String mvn = "...";
        System.out.println(f1.setMinefield(mvn));
        System.out.println(f1.showField());
        System.out.println(f1.setMinefield(inCorrect1));
        System.out.println(f1.setMinefield(inCorrect2));
        System.out.println(f1.setMinefield(inCorrect3));
        System.out.println(f1.setMinefield(correct1));
        System.out.println(f1.showField());
        System.out.println(f1.setMinefield(correct2));
        System.out.println(f1.showField());*/

        
        /*String correct3 = "........\n********\n.*.*.*.*\n..**....\n........\n*..*..*.";
        System.out.println(f1.setMinefield(correct3));
        System.out.println(f1.getNumberOfBombs());
        System.out.println(f1.getHintAt(2, 4));*/


        /*String correct2 = "*.*";
        System.out.println(f1.setMinefield(correct2));
        System.out.println(f1.getNumberOfBombs());*/


        String maven1 = "**...\n.....\n.*...";
        System.out.println(f1.setMinefield(maven1));
        System.out.println(f1.getHintAt(1, 3));
    }
}
