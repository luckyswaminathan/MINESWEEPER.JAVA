package org.cis1200.Minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileImage {
    private static final String bomb = "files/bombimageMS.png";
    private static final String one = "files/1bombaroundMS.png";
    private static final String two = "files/2bombaroundMS.png";
    private static final String three = "files/3bombaroundMS.png";
    private static final String four = "files/4bombaroundMS.png";
    private static final String five = "files/5bombaroundMS.png";
    private static final String six =  "files/6bombaroundms.png";
    private static final String seven = "files/Minesweeper_7.svg.png";
    private static final String eight = "files/unclickedMS.png";
    private static final String nine = "files/flaggedTileMS.jpeg";
    private BufferedImage img;

    private static final String clicked = "files/MS0bombs.svg";


// puts the image in for each square.
    public TileImage(int type) {

        if (type == 10) {

            try {
                img = ImageIO.read(new File(bomb));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 8) {
            try {
                img = ImageIO.read(new File(eight));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 1) {
            try {
                img = ImageIO.read(new File(one));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 2) {
            try {
                img = ImageIO.read(new File(two));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 3) {
            try {
                img = ImageIO.read(new File(three));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 4) {
            try {
                img = ImageIO.read(new File(four));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 5) {
            try {
                img = ImageIO.read(new File(five));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 6) {
            try {
                img = ImageIO.read(new File(six));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 9) {
            try {
                img = ImageIO.read(new File(seven));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else if (type == 0) {
            try {
                img = ImageIO.read(new File(clicked));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        } else {
            try {
                img = ImageIO.read(new File(seven));
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        }
    }

    public BufferedImage getIMG() {
        return img;
    }
}
