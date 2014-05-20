#=================================================
# Specification file for LSC-project NIS plugin
#
# Install LSC NIS plugin
#
# BSD License
#
# Copyright (c) 2009 - 2013 LSC Project
#=================================================

#=================================================
# Variables
#=================================================
%define lsc_nis_name	lsc-nis-plugin
%define lsc_nis_version	1.0
%define lsc_min_version	2.1.0
%define lsc_user        lsc
%define lsc_group       lsc

#=================================================
# Header
#=================================================
Summary: LSC NIS plugin
Name: %{lsc_nis_name}
Version: %{lsc_nis_version}
Release: 0%{?dist}
License: BSD
BuildArch: noarch

Group: Applications/System
URL: http://lsc-project.org

Source: %{lsc_nis_name}-%{lsc_nis_version}.jar
BuildRoot: %{_tmppath}/%{name}-%{version}-%{release}-root-%(%{__id_u} -n)

Requires(pre): coreutils
Requires: lsc >= %{lsc_min_version}

%description
This is a NIS plugin for LSC

%prep

%build

%install

rm -rf %{buildroot}

# Create directories
mkdir -p %{buildroot}/usr/%{_lib}/lsc

# Copy files
cp -a %{SOURCE0} %{buildroot}/usr/%{_lib}/lsc

%post

/bin/chown -R %{lsc_user}:%{lsc_group} /usr/%{_lib}/lsc 


%postun

%clean
rm -rf %{buildroot}

%files
%defattr(-, root, root, 0755)
/usr/%{_lib}/lsc/lsc-nis-plugin*

#=================================================
# Changelog
#=================================================
%changelog
* Sat 26 Apr 2014 - Clement Oudot <clem@lsc-project.org> - 1.0-0
- First package for LSC NIS plugin
