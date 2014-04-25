class initial_apt_update {
  exec { 'apt-get update':
    command => '/usr/bin/apt-get update',
  }
}

class package_install($packages) {
  package { $packages: 
    ensure => installed
  }
}

class nkh {

  stage { 'preinstall':
    before => Stage['package_install']
  }

  class { 'initial_apt_update':
    stage => preinstall
  }

  stage { 'package_install':
    before => Stage["main"]
  }

  class { 'package_install':
    packages => [
      'tomcat7', 'tomcat7-admin'
      ],
    stage => package_install
  }

  $web_xml = '/usr/share/tomcat7-admin/manager/WEB-INF/web.xml'
  $tomcat_users_xml = '/var/lib/tomcat7/conf/tomcat-users.xml'

  file { $web_xml:
    ensure  => present,
    source  => 'puppet:///modules/nkh/tomcat/web.xml',
    owner   => 'root',
    group   => 'root',
    mode    => '0644',
    notify  => Service['tomcat7']
  }

  file { $tomcat_users_xml:
    ensure  => present,
    source  => 'puppet:///modules/nkh/tomcat/tomcat-users.xml',
    owner   => 'root',
    group   => 'tomcat7',
    mode    => '0640',
    notify  => Service['tomcat7']
  }
  
  service { "tomcat7":
    ensure => running,
    require => [ File[$web_xml], File[$tomcat_users_xml]]
  }

}

include nkh
