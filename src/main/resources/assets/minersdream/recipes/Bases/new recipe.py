import os

material = input('What are they made of?\t')
data = input('What is the data?\t')

for item in os.scandir():
	if '.json' in item.name:
		lines = []
		with open(item.name, 'rt') as file:
			print(item.name.format(material))
			for line in file.readlines():
				new = line.replace("{}", material)
				if "()" in new:
					new = new.replace("()", input("SPECIAL DATA\t\t"))
				lines.append(new.replace("[]", data))
				
			file.close()
		with open("..\\" + item.name.format(material), 'wt') as newFile:
			newFile.write(''.join(lines))
			newFile.close()
