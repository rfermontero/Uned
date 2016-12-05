#!/bin/bash
hertz=$(getconf CLK_TCK)
PIDS=$(ls -la /proc | awk '{print $9}' | grep "^[0-9]*$")
PIDLIST=$(echo $PIDS | tr "" "\n")
counter=0

#--FETCH MEMORY AND CPU INFO TO BE USED LATER --#
memory=$(awk '/^MemTotal:/ {print $2}' < /proc/meminfo)
memoryfree=$(awk '/^MemFree:/ {print $2}' < /proc/meminfo)
buffer=$(awk '/^Buffers:/ {print $2}' < /proc/meminfo)
cache=$(awk '/^Cached:/ {print $2}' < /proc/meminfo)
swapcache=$(awk '/^SwapCached:/ {print $2}' < /proc/meminfo)
mapped=$(awk '/Mapped:/ {print $2}' < /proc/meminfo)
nr=$(awk '/nr_mapped/ {print $2}' < /proc/vmstat)
page=$(($mapped/$nr))
usedmemory=$(($memory-($memoryfree+$cache+$swapcache+$buffer+$cache)))
cpuusage=$(grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage "%"}')

for PID in $PIDLIST; do
	if [ -d /proc/$PID ]; then
		#--CALCULATE USER--#
		useruid=$(awk '/Uid/ {print $2}' 2> /dev/null < /proc/$PID/status)
		user=$(getent passwd $useruid | cut -d: -f1)
		#--CALCULATE PRIORITY--#
		PRIORITY[$counter]=$(awk '{print $18}' 2> /dev/null < /proc/$PID/stat)
		#--CALCULATE VIRTUAL MEMORY--#
		VIRTUALMEMORY[$counter]=$(awk '{print $23}' 2> /dev/null < /proc/$PID/stat)
		#--CALCULATE STATE--#
		STATE[$counter]=$(awk '{print $3}' 2> /dev/null < /proc/$PID/stat)
		#--CALCULATE CPU TIME--#
		#-- usage = 100 * (utime_after - utime_before) / (time_total_after - time_total_before); --#
		uptime=$(awk '{print $1}' < /proc/uptime)
		utime=$(awk '{print $14}' < /proc/$PID/stat)
		stime=$(awk '{print $15}' < /proc/$PID/stat)
		cutime=$(awk '{print $17}' < /proc/$PID/stat)
		cstime=$(awk '{print $16}' < /proc/$PID/stat)
		starttime=$(awk '{print $22}' < /proc/$PID/stat)
		totaltime=$(($utime+$stime+$cutime+$cstime))
		middlevalue=$((starttime / hertz))
		seconds=$(echo "scale = 2; $uptime-$middlevalue" | bc)
		middlevalue=$(echo "scale = 2;$totaltime/$hertz" | bc)
		middlevalue=$(echo "scale = 3;$middlevalue/$seconds" | bc)
		CPUUSAGE[$counter]=$(echo "scale = 2; $middlevalue*100" | bc | awk '{printf "%.2f", $0}')
		#--CALCULATE %MEMORY--#
		rss=$(awk '{print $24}' 2> /dev/null < /proc/$PID/stat)
		MEMORY[$counter]=$(echo "scale = 3; (($rss*$page)/$memory)*100" | bc 2> /dev/null | awk '{printf "%.2f", $0}' | awk '{if($0 ~ /\./) sub("\\.*0+$","");print}')
		#--CALCULATE TIME--#
		TIME[$counter]=$(date -d@$seconds -u +%H:%M:%S)
		#--CALCULATE NAME--#
		NAME[$counter]=$(awk '{print $1}' 2> /dev/null < /proc/$PID/cmdline)
		#--APPEND PROCESS INFO TO FILE--#
		printf "%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-11s %-10s\n" $PID $user ${PRIORITY[$counter]} ${VIRTUALMEMORY[$counter]} ${STATE[$counter]} ${CPUUSAGE[$counter]} ${MEMORY[$counter]} ${TIME[$counter]} ${NAME[$counter]} >> tmp.out
		counter=$((counter + 1))
	fi

	sort -k6 -nr -k7 -nr < ./tmp.out | head -10 > ./final.out

done

#--PRINT MEMORY AND CPU USAGE INFO--#
echo "Number of process running "$counter
echo "CPU Usage: "$cpuusage
echo "Total memory in KB: $memory"
echo "Total memory used in KB: $usedmemory"
echo "Total memory free in KB: $memoryfree"
#--PRINT HEADER--#
printf "%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-11s %-10s\n" PID USUARIO PR VIRT S %CPU %MEM TIME COMMAND
#--SORT DATA BY CPUUSAGE COLUMN AND APPENT TO FILE--#
sort -k6 -nr -k7 -nr <tmp.out | head -10 > ./final.out
#--PRINT DATA--#
cat ./final.out
#--REMOVE FILES--#
rm tmp.out
rm final.out
exit;



 
