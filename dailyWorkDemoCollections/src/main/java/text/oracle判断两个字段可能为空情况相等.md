```sql
BEGIN 

INSERT INTO OSSWFM.LC_FMS_ORDER_DETAILS
(CIRCLE, SSA, STATUS, ORDER_NO, POSTING_DATE, ESTAT, CUST_NAME, PHONE_NO, CUSTOMER_ACCT, SERO_ID, PENDING_TASK, ADDRESS, MOB_NUMBER, SERVICE_TYPE, SERVICE_SUB_TYPE, SALE_FRANCHISEE, WORK_FRAN_CODE, FRAN_CATEGORY, ORDER_TYPE, ORDER_SUB_TYPE, ASSIGN_TO_FRAN_TASK_DATE, LAST_MILE, TYPE_OF_MEDIA_AEND, TYPE_OF_MEDIA_BEND, BB_ACCESS_NETWOR, TESTING_DT, XFER_TO_FMS_FLAG, BB_AEND_MODEM1_COLLECTED, CABLE_ID, CABLE_TYPE, CABLE_IN_METERS, CABLE_ID_BEND, CABLE_TYPE_BEND, CABLE_IN_METERS_BEND, BB_CUST_REQ_MODEM_AEND, AEND_MODEM_ACQUISITION_TYPE, NO_OF_MODEMS_AEND, BB_CUST_REQ_MODEM_BEND, BEND_MODEM_ACQUISITION_TYPE, NO_OF_MODEMS_BEND, EXCH_CODE_ENDA, EXCH_CODE_ENDB, RF_BTS_IP_ENDA, RF_BTS_TYPE_ENDA, RF_BTS_IP_ENDB, RF_BTS_TYPE_ENDB, RETENTION_FLAG, OLT_OWNER, OLT_OWNER_ENDB, OLT_IP, OLT_IP_ENDB, NO_OF_ONT_ENDA, ONT_TYPE, ONT_ACQUISITION_TYPE, NO_OF_ONT_ENDB, ONT_TYPE_ENDB, ONT_ACQUISITION_TYPE_ENDB, PWP_WIFI_MODEL, FRAN_TASK_NAME)
SELECT
CIRCLE, SSA, STATUS, ORDER_NO, POSTING_DATE, ESTAT, CUST_NAME, PHONE_NO, CUSTOMER_ACCT, SERO_ID, PENDING_TASK, ADDRESS, MOB_NUMBER, SERVICE_TYPE, SERVICE_SUB_TYPE, SALE_FRANCHISEE, WORK_FRAN_CODE, FRAN_CATEGORY, ORDER_TYPE, ORDER_SUB_TYPE, ASSIGN_TO_FRAN_TASK_DATE, LAST_MILE, TYPE_OF_MEDIA_AEND, TYPE_OF_MEDIA_BEND, BB_ACCESS_NETWOR, TESTING_DT, XFER_TO_FMS_FLAG, BB_AEND_MODEM1_COLLECTED, CABLE_ID, CABLE_TYPE, CABLE_IN_METERS, CABLE_ID_BEND, CABLE_TYPE_BEND, CABLE_IN_METERS_BEND, BB_CUST_REQ_MODEM_AEND, AEND_MODEM_ACQUISITION_TYPE, NO_OF_MODEMS_AEND, BB_CUST_REQ_MODEM_BEND, BEND_MODEM_ACQUISITION_TYPE, NO_OF_MODEMS_BEND, EXCH_CODE_ENDA, EXCH_CODE_ENDB, RF_BTS_IP_ENDA, RF_BTS_TYPE_ENDA, RF_BTS_IP_ENDB, RF_BTS_TYPE_ENDB, RETENTION_FLAG, OLT_OWNER, OLT_OWNER_ENDB, OLT_IP, OLT_IP_ENDB, NO_OF_ONT_ENDA, ONT_TYPE, ONT_ACQUISITION_TYPE, NO_OF_ONT_ENDB, ONT_TYPE_ENDB, ONT_ACQUISITION_TYPE_ENDB, PWP_WIFI_MODEL, FRAN_TASK_NAME
FROM FRANCHISEE_PROV_ORDER_LC@LINK_CLARITY T2 WHERE NOT EXISTS (SELECT ORDER_NO FROM OSSWFM.LC_FMS_ORDER_DETAILS T1 WHERE T2.ORDER_NO = T1.ORDER_NO AND NVL(T1.FRAN_TASK_NAME, '1') = NVL(T2.FRAN_TASK_NAME, '1'))


END;
```
```sql
NVL(T1.FRAN_TASK_NAME, '1') = NVL(T2.FRAN_TASK_NAME, '1')
```