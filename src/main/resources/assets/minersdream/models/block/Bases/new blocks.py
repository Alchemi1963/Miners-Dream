import os

while True:
	material = input('What is it made of?\t')
	
	if material == '':
		break
	
	for item in os.scandir():
		if '.json' in item.name:
			lines = []
			with open(item.name, 'rt') as file:
				print(item.name.format(material))
				for line in file.readlines():
					lines.append(line.replace("{}", material))
					
				file.close()
			with open("..\\" + item.name.format(material), 'wt') as newFile:
				newFile.write(''.join(lines))
				newFile.close()
