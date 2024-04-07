import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import configuration from '@/config/configuration';
import { RedisModule } from './redis/redis.module';
import { CheckInModule } from './modules/checkIn/check-in.module';
import { ScheduleModule } from './schedule/schedule.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './entities/user.entity';
import { CheckIn, CheckInTask } from './entities/checkIn.entity';
// import { UserModule } from './modules/user/user.module';
// import { CheckIn, CheckInTask } from './entities/checkIn.entity';

@Module({
  imports: [
    ConfigModule.forRoot({
      load: [configuration],
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: async (config: ConfigService) => ({
        type: 'mysql', // or your actual db type
        host: config.get('database.host'),
        port: config.get('database.port'),
        username: config.get('database.username'),
        password: config.get('database.password'),
        database: 'BuyLife',
        entities: [User, CheckIn, CheckInTask],
        synchronize: false,
      }),
      inject: [ConfigService],
    }),
    CheckInModule,
    // UserModule,
    RedisModule,
    ScheduleModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
