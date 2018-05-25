import os, sys

type = None

def parse(material):
	global type
	string = '{\n    "parent": "minersdream:block/ores/ore_' + type + '_' + material + '"\n}'
	
	with open("ore_{}_{}.json".format(type, material), 'wt') as file:
		file.write(string)
		file.close()


if __name__ == "__main__":
	
	for arg in sys.argv:
		if arg != sys.argv[0] and arg != sys.argv[1]:
			parse(arg)
		
		elif arg == sys.argv[1]:
			type = arg
		