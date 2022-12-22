const fs = require('fs');
const soap = require('soap');
const prompt = require('prompt-sync')(); // this is a third node module : npm i prompt-sync

var url = 'http://localhost:7000/RAT';

var myOption = 0;

soap.createClientAsync(url).then(async (client) => {

    try{

        while(myOption != 4) {

            console.log('\n\t1. If you wanna get list of running proccesses press on : 1\n');
            console.log('\t2. if you wanna get the remote screenshot press on : 2\n');
            console.log('\t3. If you wanna reboot the remote system press on : 3 \n');
            console.log('\t4. Quit\n');

            myOption = prompt('\t Enter your option among the 4 options ==> ');

            // Reboot the remote system:
            if (choice == 3) {
                await client.rebootAsync(url).then((result) => {
                    if (Object.values(result[0])[0]) {
                        console.log('\tReboot done ...');
                    }
                    else {
                        console.log('\tReboot Failed...');
                    }
                }).catch((error) => {
                    console.log(error);
                });
            }

            //Get remote screenshot:
            else if (choice == 2) {
                await client.getScreenShotAsync(url).then((data) => {
                    if(data[0] == null){
                        console.log('\tRemote screenshot failed!');
                    }
                    else{
                        function saveImage(filename, data){
                            var myBuffer = Buffer.from(data,"base64")
                            fs.writeFile('./remote/'+filename, myBuffer, function(err) {
                                if(err) {
                                    console.log(err);
                                } else {
                                    console.log("\t screenshot done successfully!");
                                }
                            });
                        }
                        let randomNumber = Math.floor(Math.random() * 100);
                        saveImage("screen"+randomNumber + ".jpg", Object.values(data[0])[0]);
                    }
                }).catch((error) => {
                    console.log(error);
                });
            }
            // list of process:
            // getRunningProccess function returns an array of strings
            else if (myOption == 1){
                 
                await client.getRunningProcessAsync(url).then((data)=>{

                    if(!data){
                        console.log("\tget list of running proccesses failed ...");
                    }else{
                        console.log(date);
                        
                    }

                })
            }
        }

    }catch(err){
        console.log(err.message);
    }
}
);