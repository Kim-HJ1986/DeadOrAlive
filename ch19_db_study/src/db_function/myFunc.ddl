--자바로 표현할 때
public long getId(long val){
	return val + 10;
}

===============================================================

--sql 쿼리로 표현할 때
CREATE OR REPLACE FUNCTION get_id(intSeed number) RETURN NUMBER
IS
BEGIN
	
    RETURN intSeed + 10;
END;

select get_id(15) from dual;