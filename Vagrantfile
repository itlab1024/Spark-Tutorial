Vagrant.configure("2") do |config|
 (1..4).each do |i|
  config.vm.define "spark-standalone#{i}" do |node|
    node.vm.box = "centos/7"
    node.vm.hostname = "spark-standalone#{i}"
    node.vm.network :private_network, ip: "192.168.56.10#{i}"
    node.vm.provider :virtualbox do |v|
       v.memory = 1024
       v.cpus = 1
       v.name = "spark-standalone#{i}"
    end
  end
 end
end