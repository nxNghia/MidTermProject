#include <iostream>
#include <fstream>
#include <time.h>
#include <vector>
#include <set>

#define MAX_LENGTH 70
#define MAX_SIZE 50

using namespace std;

void init(string filename)
{
	srand(time(NULL));
	fstream f;
	f.open(filename);
	for (int i = 0; i < MAX_SIZE; ++i)
	{
		for (int j = 0; j < MAX_LENGTH; ++j)
		{
			f << rand() % 2;
		}
		f << endl;
	}
	f.close();
}

int get_max(int arr[MAX_LENGTH])
{
	int max = 0;

	for (int i = 1; i < MAX_LENGTH; ++i)
	{
		if(arr[i] > arr[max])
		{
			max = i;
		}
	}

	return arr[max] == 0 ? -1 : max;
}

void process(string filename1, string filename2)
{
	fstream f;
	f.open(filename1);
	vector<vector<int>> array2d;
	int count[MAX_LENGTH];
	for (int i = 0; i < MAX_LENGTH; ++i)
	{
		count[i] = 0;
	}

	string text;

	while(getline(f, text))
	{
		vector<int> tmp;
		for (int i = 0; i < text.length(); ++i)
		{
			tmp.push_back(text[i] - '0');
			count[i] += text[i] - '0';
		}
		array2d.push_back(tmp);
	}
	f.close();

	f.open(filename2);
	int t = 0;

	set<int> checked;
	while(checked.size() != array2d.size())
	{
		int cur_max = get_max(count);
		if(cur_max != -1)
		{
			for (int i = 0; i < array2d.size(); ++i)
			{
				for (int j = 0; j < array2d[i].size(); ++j)
				{
					if(array2d[i][cur_max] == 1 && checked.count(i) == 0)
					{
						if(array2d[i][j] == 1)
						{
							--count[j];
						}
					}
				}
				if(array2d[i][cur_max] == 1 && checked.count(i) == 0)
				{
					checked.insert(i);
					for (auto x : array2d[i])
					{
						f << x;
					}
					f << endl;
				}
			}
			f << endl;
		}else{
			break;
		}
	}
	f.close();
}

int main(int argc, char const *argv[])
{
	init(argv[1]);
	process(argv[1], argv[2]);
}