#!/bin/bash

if [ "$#" -ne 2 ]; then
        echo "Illegal number of parameters";
        exit -1;
fi

configFile=$1
serverId=$2
cd /opt/teeworlds
./teeworlds_srv -f $configFile > logs/$serverId/std.log 2> logs/$serverId/err.log
