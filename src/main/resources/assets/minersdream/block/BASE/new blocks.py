import os, sys

def parse(material):	
	if material != '':
		for item in os.scandir():
			if '.json' in item.name:
				lines = []
				
				with open(item.name, 'rt') as file:
					print(item.name.format(material))
					for line in file.readlines():
						lines.append(line.replace("{}", material))
						
					file.close()
				with open("../" + item.name.format(material), 'wt') as newFile:
					newFile.write(''.join(lines))
					newFile.close()

if __name__ == "__main__":
	
	for arg in sys.argv:
		if arg != sys.argv[0]:
			parse(arg)