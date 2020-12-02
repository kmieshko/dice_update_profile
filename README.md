# Update Dice Profile using selenium webdriver

*NOTICE* This app created for two Dice profiles, place links from your profile to line 36 and 38. If you have hust one Dice pofile -> delete line *38* and *39* and change variable **profileQty = 1**

Instruction for Eclipse IDE:

1. *File* -> *Open Project From File System*
2. Put into *28* and *29* lines in */dice_update_profile/src/dice_update_profile/DiceUpdateProfile.java* **password** and **email** from Dice
3. Put into line *36* and *38* your Dice profile links
4. *Project* -> *Properties* -> [put in search] *Java Build Path* -> *Add External JARs* from **selenium-java-3.141.59** folder in the root of the repo (two JARs in the root and another ones from *libs* folder)
5. Update Chrome to newest version
6. Download actual version of <a href="https://chromedriver.chromium.org/">chromedriver</a> to C:/ and unzip archive
 * Final destination of chromedriver should be **C:/chromedriver_win32/chromedriver.exe**
7. *File* -> *Export* -> *Runnable JAR file* -> select *configuration* from this project and check *Extract required libraries into JAR*
8. Create Task in *Windows Task Scheduler* for running .jar application every day
9. **Time to time update chrome and chromedriver**
