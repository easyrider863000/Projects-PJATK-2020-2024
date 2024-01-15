print("Enter objs. For break enter \"b\".")
print()
processes = []
id = 0
#while True:
#    try:
#        a = int(input("Enter time appear --> "))
#        b = int(input("Enter continued time --> "))
#        print()
#        processes.append({"id":id, "t_appear":a,"t_continue":b,"t_wait":0,"t_todo":0})
#        id+=1
#    except:
#        break
processes.append({"id":0, "t_appear":1,"t_continue":1,"t_wait":0,"t_todo":0})
processes.append({"id":1, "t_appear":2,"t_continue":4,"t_wait":0,"t_todo":0})
processes.append({"id":2, "t_appear":9,"t_continue":4,"t_wait":0,"t_todo":0})
processes.append({"id":3, "t_appear":6,"t_continue":9,"t_wait":0,"t_todo":0})
processes.append({"id":4, "t_appear":0,"t_continue":2,"t_wait":0,"t_todo":0})


processes = sorted(processes, key=lambda x: x['t_appear'])
i = 1
processes[0]['t_start'] = processes[0]['t_appear']
while i<processes.__len__():
    processes[i]['t_start'] = processes[i-1]['t_start']+processes[i-1]['t_continue']
    i+=1
print(processes)
processes = sorted(processes, key=lambda x: x['id'])


for item in processes:
    item["t_wait"] = (item["t_start"]-item["t_appear"])
    item["t_todo"] = (item["t_start"]-item["t_appear"]+item["t_continue"])


print("numer\tczas poczatku\tczas oczekiwania\\reakcji\tchas obrotu\\przetwarzania")
i=0
avg_wait_time = 0
avg_todo_time = 0
for item in processes:
    print(str(i)+"\t\t\t"+str(item["t_start"])+"\t\t\t\t"+str(item["t_wait"])+"\t\t\t\t\t\t\t"+str(item["t_todo"]))
    i+=1
    avg_wait_time+=item["t_wait"]
    avg_todo_time += item["t_todo"]
avg_wait_time/=i
avg_todo_time/=i


print("tablica:")
i=0
numbs_of_current_time=0
processes = sorted(processes, key=lambda x: x['t_appear'])
tmp_processes = processes

current_proc = "-"
while i<50:
    if len(tmp_processes) == 0:
        current_proc = str()
    else:
        current_proc = str(tmp_processes[0]['id'])

    if i>=tmp_processes[0]['t_start'] and numbs_of_current_time<tmp_processes[0]['t_continue']:
        numbs_of_current_time += 1

        #tmp_processes = tmp_processes[1:]
    elif numbs_of_current_time==tmp_processes[0]['t_continue']:
        tmp_processes = tmp_processes[1:]
        current_proc="-"
    else:
        numbs_of_current_time = 0

    print(str(i),current_proc,"\toczekuja:",",".join([str(j['id']) for j in tmp_processes[1:]]))
    i+=1


print("sredni czas oczekiwania\\reakcji =",avg_wait_time)
print("sredni czas obrotu\\przetwarzania =",avg_todo_time)
