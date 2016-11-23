#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
struct ci_less : std::binary_function<std::string, std::string, bool>
    {
    // case-independent (ci) compare_less binary function
    struct nocase_compare : public std::binary_function<unsigned char,unsigned char,bool> 
    {
      bool operator() (const unsigned char& c1, const unsigned char& c2) const {
          return tolower (c1) < tolower (c2); 
      }
    };
    bool operator() (const std::string & s1, const std::string & s2) const {
      return std::lexicographical_compare 
        (s1.begin (), s1.end (),   // source range
        s2.begin (), s2.end (),   // dest range
        nocase_compare ());  // comparison
    }
};
  
int main()
{
    int N; // Number of elements which make up the association table.
    cin >> N; cin.ignore();
    std::map< std::string, std::string, ci_less > fileType;
    int Q; // Number Q of file names to be analyzed.
    cin >> Q; cin.ignore();
    string files[Q];
    for (int i = 0; i < N; i++) {
        string EXT; // file extension
        string MT; // MIME type.
        cin >> EXT >> MT; cin.ignore();
        fileType[EXT] = MT;
        cerr << EXT << endl;
    }
    for (int i = 0; i < Q; i++) 
    {
        string FNAME; // One file name per line.
        getline(cin, FNAME);
        std::size_t found = FNAME.find_last_of('.');
        if (found != string::npos)
        {
            string fileExtension = FNAME.substr(found+1);
            if (fileType.find(fileExtension) != fileType.end())
            {
                cout << fileType[fileExtension] << endl;
                continue;
            }
        }
            
        cout << "UNKNOWN" << endl;
    }

    // Write an action using cout. DON'T FORGET THE "<< endl"
    // To debug: cerr << "Debug messages..." << endl;


    // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
}

