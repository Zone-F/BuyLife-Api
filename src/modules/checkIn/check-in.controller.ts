import {
  Body,
  Controller,
  Post,
  // Get,
  // Param,
  // Delete,
  // Put,
} from '@nestjs/common';
import { CheckInService } from './service/check-in.service';
import { CheckInTaskService } from './service/check-in-task.service';
import { CheckInDTO, CreateTaskDTO } from '@/modules/checkIn/dto/check-in.dto'; // 确保DTO的路径正确

@Controller('check-in')
export class CheckInController {
  constructor(
    private readonly checkInService: CheckInService,
    private readonly checkInTaskService: CheckInTaskService,
  ) {}

  // 新增打卡任务
  @Post('task')
  async addCheckInTask(@Body() taskDto: CreateTaskDTO) {
    return await this.checkInTaskService.createCheckInTask(taskDto);
  }

  // // 如果需要，为了撤销打卡，可以由PUT方法实现状态的反转
  // @Put('undo/:checkInId')
  // async reverseCheckIn(@Param('checkInId') checkInId: number) {
  //   return await this.checkInService.reverseCheckIn(checkInId);
  // }

  @Post()
  async createCheckIn(@Body() checkInDTO: CheckInDTO) {
    return await this.checkInService.createCheckIn(checkInDTO);
  }

  // @Get(':userId')
  // async getCheckIns(@Param('userId') userId: number) {
  //   return await this.checkInService.getCheckInsForUser(userId);
  // }

  // // 假设用户可以通过ID来撤销打卡
  // @Delete(':checkInId')
  // async undoCheckIn(@Param('checkInId') checkInId: number) {
  //   return await this.checkInService.undoCheckIn(checkInId);
  // }

  // // 获取用户的打卡任务列表
  // @Get('tasks/:userId')
  // async getCheckInTasks(@Param('userId') userId: number) {
  //   return await this.checkInService.getCheckInTasksForUser(userId);
  // }

  // // 删除打卡任务，假设我们通过任务ID来删除
  // @Delete('task/:taskId')
  // async removeCheckInTask(@Param('taskId') taskId: number) {
  //   return await this.checkInService.removeCheckInTask(taskId);
  // }
}
