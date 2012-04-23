NIS / YP plugin
---------------

This plugin enables the usage of any NIS compliant directory as source database. It can retrieve
any standard map that is available (user, group, ...) but also custom maps (automounts, ...)

This feature has been contributed as a plugin because of the SUN binary license under which the NIS library
is released - see the corresponding part of the LICENSE.txt file.

To use this connector, required connection setting is the URL :
<nisConnection>
	<id>nis-src-conn</id>
	<url>nis://127.0.0.1/test.org</url>
</nisConnection>

Complete this with the map name inside the service declaration :
<nisSourceService>
    <name>nis-source-service</name>
	<connection reference="nis-src-conn"/>
	<map>passwd.byname</map>
</nisSourceService>
