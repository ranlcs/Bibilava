@echo off
aapt package -f -m -J src -M AndroidManifest.xml -S res -I "C:\Program Files (x86)\Android\platforms\android-4.1.2\Android.jar" 
javac -d obj -cp src -bootclasspath "C:\Program Files (x86)\Android\platforms\android-4.1.2\Android.jar" src/com/lcs/Bibilava/*.java
dx --dex --output=bin/classes.dex obj
@echo on
