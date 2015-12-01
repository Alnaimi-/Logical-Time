setwd("~/Idea Projects/DSSCW2/")
data <- read.csv("experiment.csv")

data_mean <- data.frame(threads = integer(0), events = integer(0), comms = integer(0), 
                        pairs = double(0), non.pairs = double(0), ratio = double(0))

for(index in 1:(nrow(data)/100) {
  seq <- (((index-1)*100)+1):(index*100)
  data_mean[index, ] <- c(data$Processes[index*100], data$Events[index*100], 
                          data$Communications[index*100], 
                          mean(data$Pairs[seq]), mean(data$Non.pairs[seq]), 0)
}

data_mean$ratio <- c(data_mean$pairs/(data_mean$pairs+data_mean$non.pairs))

library(ggplot2)
plot <- ggplot(data_mean, aes(x=events, y=ratio, color=as.factor(threads)))
(plot + geom_point() + facet_grid(. ~ comms) + 
   scale_y_continuous(breaks=seq(0, 1, 0.05)) +
   scale_x_continuous(breaks = seq(0, 1000, 200)))

