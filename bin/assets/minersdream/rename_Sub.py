import os

files = []

forbidden = ['.png', '.py']

def scan(dir='.'):
	for item in os.scandir(dir):
		if item.is_file():
			if all([item.name.find(word) == -1 for word in forbidden]):
				files.append(item.path)
		elif item.is_dir():
			scan(item.path)

scan()

occurences = 0
for file in files:
	with open(file, 'r') as f:

		print(file)

		indexLine = 0
		lines = f.readlines()
		
		for line in lines:
			
			occurences += line.count('lm.')
			occurences += line.count('lm:')
			
			
			lines[indexLine] = lines[indexLine].replace('lm.', 'minersdream.')
			lines[indexLine] = lines[indexLine].replace('lm:', 'minersdream:')
			indexLine += 1
		
		f.close()
		with open(file, 'w') as f:
			f.write(''.join(lines))
			f.close()
		#print(''.join(lines))
		
		os.system('cls')

print(occurences)
	
