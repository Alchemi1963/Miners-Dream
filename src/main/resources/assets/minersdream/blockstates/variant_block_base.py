import os, sys

type = None

def parse(material, last = False):
	global type
	string = '        "variant=' + material + '": { "model": "minersdream:block_' + type + '_' + material + '"},\n'
	
	if last:
		string = string[:-2] + '\n    }\n\
}'
	
	with open("block_{}.json".format(type), 'at') as file:
		file.write(string)
		file.close()


if __name__ == "__main__":
	
	with open("block_{}.json".format(sys.argv[1]), 'wt') as file:
		file.write('{\n\
    "variants":\n\
    {\n')
		file.close()
	
	for arg in sys.argv:
		if arg != sys.argv[0] and arg != sys.argv[1] and arg != sys.argv[-1]:
			parse(arg)
		
		elif arg == sys.argv[1]:
			type = arg
			
		elif arg == sys.argv[-1]:
			parse(arg, True)
		