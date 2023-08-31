# DownloadFileFromURL
Hello there!
You are welcome to contribute in this

How to Integrate!

////////////////////////// For Gradle
Step 1. Add the JitPack repository to your build file
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 
Step 2. Add the dependency
// In Module Levele build.gradle
dependencies {
	        implementation 'com.github.Me-Usman-Raoof:DownloadFileFromURL:Tag'
	}

///////////////////////// For Maven
Step 1. Add the JitPack repository to your build file

<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

 Step 2. Add the dependency

 <dependency>
	    <groupId>com.github.Me-Usman-Raoof</groupId>
	    <artifactId>DownloadFileFromURL</artifactId>
	    <version>1.0.0</version>
	</dependency>

Please check the latest version available
