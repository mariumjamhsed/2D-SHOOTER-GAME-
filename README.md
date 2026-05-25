# 2D Space Shooter 🚀

So I made a little space shooter game in Java. You move a ship around, shoot enemies falling from the top, and try not to die. Pretty classic stuff but I had fun building it.

## How to play

- **Left/Right arrows** to move
- **Spacebar** to shoot
- Don't let the pink things touch you 💀
- Each enemy you shoot = +1 score

That's it really. It keeps going until an enemy hits you.

## What I used

Just Java and Swing — no fancy libraries or game engines. Wanted to actually understand how games work under the hood instead of relying on a framework to do everything for me.

The code is split into a few classes:
- `Player` — the ship you control
- `Bullet` — the bullets you shoot
- `Enemy` — the things trying to ruin your day
- `GamePanel` — handles the actual game loop, drawing, and collisions
- `SpaceShooterGame` — main class to launch everything

## Running it

You'll need Java installed (JDK 8 or above should be fine).

```bash
javac SpaceShooterGame.java
java SpaceShooterGame
```

Or just open it in IntelliJ / Eclipse / VS Code and hit run.

## What I figured out while making this

Honestly learned a lot more than I expected:

- How game loops actually work (turns out it's just a Timer firing every 30ms 😭)
- Collision detection isn't magic — it's literally just checking if two rectangles overlap
- ArrayLists are super handy for stuff that keeps appearing and disappearing (like bullets)
- Why people split code into multiple classes — debugging one giant file would've been a nightmare

## Stuff I want to add later

Got a list of things I'd love to add when I get time:

- [ ] Actual ship and enemy images instead of boring rectangles
- [ ] Some sound effects (pew pew)
- [ ] Background music
- [ ] Make it harder over time (faster enemies, more spawns)
- [ ] A restart button so you don't have to close and reopen the whole thing
- [ ] Power-ups maybe? Like a shield or double bullets
- [ ] Save the high score somewhere

## Screenshot

*(gonna add one once I take a good one)*

## About me

I'm Marium, currently studying digital electronics and programming. This was a side project to practice OOP in Java because honestly, learning OOP from textbook examples like "Animal class extends Dog" gets boring fast. Making a game was way more fun.

If you have suggestions or wanna point out where my code could be better, please do — I'm still learning!

---

⭐ Star this if you liked it!
