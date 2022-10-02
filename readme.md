# Getting Started with the EWS Java API

The Exchange Web Services (EWS) Java API provides a managed interface for developing Java applications that use EWS.
By using the EWS Java API, you can access almost all the information stored in an Office 365, Exchange Online, or Exchange Server mailbox. However, this API is in sustaining mode, the recommended access pattern for Office 365 and Exchange online data is [Microsoft Graph](https://graph.microsoft.com)

## Modifications
This fork is based on https://github.com/OfficeDev/ews-java-api.
The modifications are:
* Replaced javax packages with jakarta packages (Jakarta EE compatible)
* Removed dependency to joda-time (java.time is used)
* Removed dependency to commons-io (replaced with standard java classes)
* Removed dependency to commons-lang3 (replaced with standard java classes)
* Migrated from junit4 to junit5
* Dependencies are updated (httpclient 4.5.x, httpcore 4.4.x, commons-codec 1.11, commons-logging 1.2)
* Removed animal-sniffer plugin as it only works up to java 1.8
* Java 11 required

## Support statement

Starting July 19th 2018, Exchange Web Services (EWS) will no longer receive feature updates. While the service will continue to receive security updates and certain non-security updates, product design and features will remain unchanged. This change also applies to the EWS SDKs for Java and .NET. More information here: https://developer.microsoft.com/en-us/graph/blogs/upcoming-changes-to-exchange-web-services-ews-api-for-office-365/


## Getting started resources

Please see the [Getting Started Guide](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide) on our wiki for an introduction to this library.

## Using the library
Please see [this wiki-entry](https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide#using-the-library) on how to include the library in your project

### Maven / Gradle
For Documentation on how to use _ews-java-api_ with maven or gradle please refer to [this section in our wiki](https://github.com/OfficeDev/ews-java-api/wiki#maven--gradle-integration). 

### Building from source
To build a JAR from the source yourself, please see [this page](https://github.com/OfficeDev/ews-java-api/wiki/Building-EWS-JAVA-API).


This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/). For more information, see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.
