SimpleFileVirusScan
-----

This service offers a small web page to upload a file and check these file against a virus scanner.  
Similar project to [virustotal](https://www.virustotal.com/gui/), just in open source with 
[clamav](https://www.clamav.net/) as virus scanner.  

### Installation

See docs/INSTALL.md

### Contribute / Add new virus scanner

If you want to add a new virus scanner, you can simply implement the 
`net.ngotzmann.SimpleFileVirusScan.virusscanner.VirusScanner` interface.  
Your virus scanner bean must be spring managed!   
  
Set the `virusscanner.beanName=yourVirusScannerBeanName` application property to your beanName.  
To test if your virus scanner work, maybe adjust and execute the 
`net.ngotzmann.SimpleFileVirusScan.virusscanner.FileServiceIntegrationTest` class.  
