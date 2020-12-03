@echo off
aapt package -f -m -F bin/Bibilava.unaligned.apk -M AndroidManifest.xml -S res -I "C:\Program Files (x86)\Android\platforms\android-4.1.2\Android.jar"
cd bin
aapt add Bibilava.unaligned.apk classes.dex
aapt list Bibilava.unaligned.apk
keytool -genkeypair -validity 365 -keystore mykey.keystore -keyalg RSA -keysize 2048
zipalign -f 4 Bibilava.unaligned.apk Bibilava.apk
apksigner sign --ks mykey.keystore Bibilava.apk
cd..
@echo on
