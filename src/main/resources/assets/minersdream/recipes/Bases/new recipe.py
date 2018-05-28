import os, sys

def parse(material, data, itemname):

	for item in os.scandir():
		if '.txt' in item.name:
			lines = []
			with open(item.name, 'rt') as file:
				print(item.name.format(material))
				for line in file.readlines():
					new = line.replace("{}", material)
					new = new.replace("@", itemname)
					lines.append(new.replace("[]", data))
					
				file.close()
			if not os.path.exists(".." + os.path.sep + material): os.mkdir(".." + os.path.sep + material)
			
			with open( ".." + os.path.sep  + material + os.path.sep + item.name.format(material).replace(".txt", ".json") , 'wt') as newFile:
				newFile.write(''.join(lines))
				newFile.close()
			
if __name__ == "__main__":
	args = sys.argv[1:-1]
	
	index = 0
	for arg in args:
		index += 1
		
		if index%2 != 0:
			parse(arg, args[index], sys.argv[-1])
	
