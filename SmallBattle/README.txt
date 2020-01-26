README

BELLS AND WHISTLES:
The bells and whistles I added were some beautification.
I had some animation for buttons, I tinted an the monster image green when poisoned
and untinted when the poison DOT ended, and I made custom buttons. 
I also included the fact that you can name your character, but I didn't get to actually
put that name anywhere. The poison DOT is also something I designed that I thought was a 
cool feature. Instead of using serializable, I used parcelable, which is supposedly
10x faster, to send my hero object to the next activity. I used something called a 
CountDownTimer to handle when I can attack and how long the poison DOT lasts. For the
enemy attack, I used handler.postDelayed() for the actual credit in the HW reqs. I 
have a dynamic results screen, depending on who died, the screen looks vastly different.

BUGS: 
I, for some reason, couldn't get the hero's maximum mana to display properly in the
battle screen. I found a couple errors in code that would cause this and fixed them,
but there are more to find.

The victory or loss screen has to wait for the CountDownTimer to end before launching
when a character dies, I think. 

MISC:
I used some deprecated code. I tried to future proof and I actually didn't find the
code I was using that was deprecated because I didn't look. Thank you for reading!