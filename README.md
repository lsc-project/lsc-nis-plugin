# LSC NIS / YP plugin

[![Build Status](https://travis-ci.org/lsc-project/lsc-nis-plugin.svg?branch=master)](https://travis-ci.org/lsc-project/lsc-nis-plugin)

> **_NOTE:_** Minimal plugin version: 1.0, Minimal required LSC version: 2.1

Presentation
============

This source service enables getting data from a RFC 2307 compliant NIS service. You will get a posixAccount complete object : 

* uid,
* uidnumber,
* gidnumber,
* gecos,
* loginShell,
* homeDirectory,
* cn,
* and correct objectClass values of course :)

> **_NOTE:_** This service is designed as a plugin because it is based upon the Sun/Oracle JNDI provider which is not released under a free license and must be downloaded by final users.

Installation
============

Get the NIS plugin. Then copy the plugin (.jar file) inside LSC lib directory. (for example `/usr/lib/lsc`)

Make sure you also have the JNDI NIS library. If you don't have it, copy it into your JRE libraries (`${JAVA_HOME}/jre/lib/ext`)

Configuration
=============

Connection
----------

Create a NIS connection:

```
<pluginConnection implementationClass="org.lsc.plugins.connectors.nis.generated.NisConnectionType">
  <name>nis-src-conn</name>
  <url>nis://NIS-SERVER-ADDRESS/lsc-project.org</url>
  <username>unused</username>
  <password>unused</password>
</pluginConnection>
```

> **_NOTE:_** `username` and `password` are mandatory for a LSC connection, but unused for NIS.

Source service
--------------

Create the source service:

```
<pluginSourceService implementationClass="org.lsc.plugins.connectors.nis.NisSrcService">
  <name>nis-source-service</name>
  <connection reference="nis-src-conn" />
  <nis:nisSourceServiceSettings>
    <name>nis-src-service</name>
    <connection reference="nis-src-conn" />
    <nis:map>passwd.byname</nis:map>
  </nis:nisSourceServiceSettings>
</pluginSourceService>
```

Here you can change the NIS map to synchronize groups:

```
<nis:map>group.byname</nis:map>
```

Run the connector
==================

To run the connector, you need to adjust JAVA_OPTS first:

```
export JAVA_OPTS=-DLSC.PLUGINS.PACKAGEPATH=org.lsc.plugins.connectors.nis.generated
```

Then, run the connector in a standard way:

```
bin/lsc -s all -f nis-cfg-dir/
```
