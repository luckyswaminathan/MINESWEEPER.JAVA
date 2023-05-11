=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 16272037
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays - used for the board of Pieces - there was no feedback so I assume this works for the 2D array - only
  used one and just used getters to translate class to class.

  2. I/O - used buffered readers and writers to store file states, didn't end up instituting all 3 skill types(as
  I couldn't figure out how to update to a different skill level in the JPanel). The methodology for this was
  I used the skill level (1,2) to define the row/cols, and then I put 2 numbers per bomb(row, column) in the next lines

  3. Collections and Mapping - after figuring out that subtyping and inheritance do not really work for minesweeper,
  I decided instead to implement a linked list structure to store the flags used.
  A linked list is justified here over an
  array as arrays have set lengths, and for a minesweeper game, we do not know how many flags will be played


  4.JUnit Testing - I added tests for checking win and loss conditions, I also added tests for playing a turn, and
  flagging. I also added a test to see if the game state was working, whcih it is. I also added a test for the linked
  list to see if the turn order was correct.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

IOState - enables files to be stored of the game

MineSweeperGame - deals with all the game mechanics- whether win or lost, playing each turn, and
updating the amount of clicks

MSBoard- handles all the graphics and conects them to MineSweeperGame - the jpanels and painting components

RunMineSweeper - runs the game and sets up the JPanel

 Piece - base unit of the board, defines each square in the board and gives it attributes necessary for the game

tileIMG- all the images to use for each square

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

Yes, I fully forgot about the requirements and when I finished I hadn't implemented all of them. This has taken more
time than coding the entire game

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

I would say it is most definitely inefficient, and runs slowly. I would say the private state is encapsulated well,
but I would optimize the classes if I had the chance.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.


I used the minesweeper game images from the mobile app for the ipad from like 2010(first images on google)
