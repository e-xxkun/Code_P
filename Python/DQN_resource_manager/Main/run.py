"""
The DQN improvement: Prioritized Experience Replay (based on https://arxiv.org/abs/1511.05952)

View more on my tutorial page: https://morvanzhou.github.io/tutorials/

Using:
Tensorflow: 1.0
gym: 0.8.0
"""


from RL_brain import DQNPrioritizedReplay
import matplotlib.pyplot as plt
import tensorflow.compat.v1 as tf
import numpy as np
import copy
import Env

MEMORY_SIZE = 200000
VUE_NUM = 50
CUE_NUM = 50
SLOT_TOTAL = 1000

sess = tf.Session()
with tf.variable_scope('DQN_with_prioritized_replay'):
    RL_prio = DQNPrioritizedReplay(
        n_actions=VUE_NUM*3, n_features=VUE_NUM*5+CUE_NUM, memory_size=MEMORY_SIZE,
        e_greedy_increment=0.00005, sess=sess, prioritized=True, output_graph=True,
    )
sess.run(tf.global_variables_initializer())

def train(RL):
    Iter_num = 10000
    total_steps = 0
    env = Env.Env(CUE_NUM, VUE_NUM)
    vehicles = []
    for _ in range(VUE_NUM):
        vehicles.append(Env.V2V_Vehicle(np.random.randint(0,20), [30, 36], [70, 80], 3))

    for i_episode in range(Iter_num):
        env_ = copy.deepcopy(env)
        vehs = copy.deepcopy(vehicles)

        state = env_.reset(vehs)
        for slot_cur in range(SLOT_TOTAL):

            action = RL.choose_action(state)
            state_, reward, end = env_.update_state(slot_cur, vehs, action)
            RL.store_transition(state, action, reward, state_)

            if total_steps > MEMORY_SIZE:
                RL.learn()

            if end:
                print('episode ', i_episode, ' finished')
                break
            
            for vehicle in vehs:
                vehicle.run(slot_cur + 1)

            state = state_
            total_steps += 1
    return [0,0]

his_prio = train(RL_prio)

# compare based on first success
# plt.plot(his_prio[0, :], his_prio[1, :] - his_prio[1, 0], c='r', label='DQN with prioritized replay')
plt.legend(loc='best')
plt.ylabel('total training time')
plt.xlabel('episode')
plt.grid()
plt.show()


