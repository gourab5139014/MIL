#This file populates the ck.data file which provides values of C,K parameters in CSV
#Modify the following definitions of c and K. THEY SHOULD BE OF EQUAL LENGTH
c= 1..10
k= 1..10

File.open('ckIRIS.data','w') do |f|
	c.zip(k).each do |first,second|
#		f.printf("%d,%d\n",first,second)
		f.puts "#{first},#{second}\n"
	end
end
