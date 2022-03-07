# Docker Overview

## Boot Loaders
   - Boot loader system utility gets installed in MBR(Master Boot Record) of your Hard Disk
   - Whenever the system is booted, CPU kind of initiates BIOS and BIOS after system self-test
     it instructs the CPU to run the Boot Loader
   - Boot Loader then scans your hard disk looking for any OS
   - In case Boot Loader detects more than one OS installed on your system it then give a menu
     for you to choose which OS you wish to boot into
   GRUB
    version 1
    version 2
   LILO (Linux Loaders)

## Virtualization
  - Hypervisor
  - Hypervisor is a general term that refers to Virtualization software
  - with this technology 
      - you can boot many OS side by side on the same PC/Desktop/Workstation/Server
      - is a combination of Hardware + Software
  - Processors that supports Virtualization feature 
      Intel
        - VT-X
      AMD
        - AMD-V
  
  - Heavy weight
      - Virtual Machines or the Guest OS they require dedicated hardware resources
           - Dedicated CPU cores
           - Dedicated RAM
           - Dedicated Storage
  
  - If you have a Processor that support Virtualization, you need to ensure that the virtualization
    feature is enabled in the BIOS.

  - How many Virtual Machine(Guest OS)
      - is limited by the Hardware resources available in the system
      - Primary factor is Processor( no of cpu cores in the Processor )
      - Amount of Random Access Memory available
      - Storage ( Hard Disk capacity )
     
  - Some vendor spectific virtualization softwares
      VMWare
        - VMWare workstation Pro
        - VMWare Fusion ( Mac )
        - VMWare vSphere (Bare metal Hypervisor)
      Microsoft
        - Hyper-v ( Comes with Windows 10 Pro or later out of the box )
      Oracle
        - VirtualBox (Free)

      Parallels ( Mac )
      KVM
       - opensource and free
       - Kernel Virtual Manager


Before the Virtualization technology came into the Industry
  - Let's say your organization needs 10000 webserver running in its own OS
  - How many physical servers are required to setup 10000 web servers?


## Virtualization vs 
