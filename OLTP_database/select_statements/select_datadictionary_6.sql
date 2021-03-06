@output.sql;

SELECT CT_COL.TABLE_NAME "CHILD TABLE_NAME",
       CT_COL.CONSTRAINT_NAME "CHILD FK CONSTRAINT",
       CT_COL.COLUMN_NAME "CHILD COLUMN_NAME",
       CT_COL.POSITION "CHILD COL POSITION",
       CT_CON.DELETE_RULE "CHILD DELETE_RULE",
       CT_CON.DEFERRABLE,
       CT_CON.DEFERRED,
       CT_CON.STATUS,
       PT_COL.TABLE_NAME "PARENT TABLE_NAME",
       PT_COL.CONSTRAINT_NAME "PARENT PK CONSTRAINT",
       PT_COL.COLUMN_NAME "PARENT COLUMN_NAME",
       PT_COL.POSITION "PARENT COL POSITION"
FROM USER_CONS_COLUMNS CT_COL, USER_CONSTRAINTS CT_CON,
     USER_CONS_COLUMNS PT_COL
WHERE CT_COL.TABLE_NAME = CT_CON.TABLE_NAME AND
      CT_CON.TABLE_NAME = 'FERIENWOHNUNG' AND
      CT_CON.CONSTRAINT_TYPE = 'R' AND
      CT_COL.CONSTRAINT_NAME = CT_CON.CONSTRAINT_NAME AND
      CT_CON.R_CONSTRAINT_NAME = PT_COL.CONSTRAINT_NAME;