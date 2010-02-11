#ifndef DATABASE_H
#define DATABASE_H

#include <stdlib.h>
#include <mysql/mysql.h>
#include "global.h"


//Try to fetch all neede information about the experiment
//we want to execute from the database.
status dbFetchExperimentData(experiment *e);

//Try to fetch a new job for processing from the database.
//If all goes well, the function returns zero.
//If no job could be fetched, the return value is non-zero
//and s gives further information about the reason. s is set to
// - success if there's no job left an no error occured
// - sysError if an error in a system function occured
// - dbError if an error in the database occured.
int dbFetchJob(job* j, status* s);

//Store information about the job j that just started running
status dbUpdate(const job* j);


#endif
