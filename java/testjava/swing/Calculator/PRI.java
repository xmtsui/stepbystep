public class PRI {
	static int getIsp(char opr){
        if(opr=='+'||opr=='-')
            return 3;
        else if(opr=='*'||opr=='/')
            return 5;
        return 0;
    }
    static int getIcp(char opr){
        if(opr=='+'||opr=='-')
            return 2;
        else if(opr=='*'||opr=='/')
            return 4;
        return 0;
    }
}
