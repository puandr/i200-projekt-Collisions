# i200 Projekt
Projektiks oleks väike mäng.
Mängualal alustad ühe mänguväljaku otsas, eesmärk jõuda teise mänguväljaku otsani. Mängija ise on keskmise suurusega objekt. Mänguväljakul suvaliselt liiguvad teised objektid. Kui nad põrkavad omavahel või mängija objektiga kokku siis arvutatakse objektide suurusi ja liikumisuundi ning peale põrkamist nad käituvad vastavalt (nt kui suur ring põrkab väiksega, siis väike ring põrkab tagasi suurema kiirusega ja suur liigub edasi natukene muutes suunda ja jäädes natukene aeglasemaks). Aga täpne põrkamise algoritm on veel täpsustamisel.


ToDo
LOGIC

- Next game
- Actors bounciong 10 pixels before the edge (right & bottom)

DESIGN
- Game Over Scene
    - Transparency 70%
    - Button "Play again"
- Winning Scene
    - Congrats
- Start Game scene

Maybe
- Timer
- Player name
- Score table

DONE
- Main hero can't go outside the gameboard
- Collision between opponents (USEFUL: https://gamedevelopment.tutsplus.com/tutorials/when-worlds-collide-simulating-circle-circle-collisions--gamedev-769)
- Finish point